package org.teamvoided.dusk_debris.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import org.teamvoided.dusk_debris.block.entity.FanBlockEntity
import org.teamvoided.dusk_debris.init.DuskBlockEntities

open class FanBlock(val strength: Int, settings: Settings) :
    BlockWithEntity(settings) {
    public override fun getCodec(): MapCodec<FanBlock> {
        return CODEC
    }

    init {
        this.defaultState = stateManager.defaultState
            .with(ACTIVE, false)
            .with(POWERED, false)
            .with(FACING, Direction.UP)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(ACTIVE, POWERED, FACING)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return FanBlockEntity(pos, state)
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return defaultState.with(FACING, ctx.side)
    }

    override fun onBlockAdded(state: BlockState, world: World, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (oldState.block != state.block && world is ServerWorld) {
            this.setState(state, world, pos)
        }
    }

    override fun neighborUpdate(
        state: BlockState,
        world: World,
        pos: BlockPos,
        block: Block,
        fromPos: BlockPos,
        notify: Boolean
    ) {
        if (world is ServerWorld) {
            this.setState(state, world, pos)
        }
    }

    fun setState(state: BlockState, world: ServerWorld, pos: BlockPos?) {
        val bl = world.isReceivingRedstonePower(pos)
        if (bl != state.get(POWERED)) {
            var blockState = state
            if (!state.get(POWERED)) {
                blockState = state.cycle(ACTIVE)
                world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos)
                world.playSound(
                    null as PlayerEntity?,
                    pos,
                    if (blockState.get(ACTIVE)) SoundEvents.BLOCK_COPPER_BULB_TURN_ON
                    else SoundEvents.BLOCK_COPPER_BULB_TURN_OFF,
                    SoundCategory.BLOCKS
                )
            }
            world.setBlockState(pos, blockState.with(POWERED, bl), 3)
        }
    }

    override fun hasComparatorOutput(state: BlockState): Boolean = true

    override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos): Int {
        val blockEntity: FanBlockEntity = world.getBlockEntity(pos) as FanBlockEntity
        return if (world.getBlockState(pos).get(ACTIVE)) blockEntity.windLength else 0
    }

    override fun onSyncedBlockEvent(state: BlockState?, world: World, pos: BlockPos?, type: Int, data: Int): Boolean {
        super.onSyncedBlockEvent(state, world, pos, type, data)
        val blockEntity: BlockEntity? = world.getBlockEntity(pos)
        return blockEntity?.onSyncedBlockEvent(type, data) ?: false
    }

    override fun <T : BlockEntity> getTicker(
        world: World,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if (state.get(ACTIVE)) {
            checkType(type, DuskBlockEntities.FAN_BLOCK, FanBlockEntity::tick)
        } else {
            null
        }
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING)))
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(FACING)))
    }

    companion object {
        val CODEC: MapCodec<FanBlock> = createCodec { settings: Settings ->
            FanBlock(
                15,
                settings
            )
        }
        val POWERED: BooleanProperty = Properties.POWERED
        val ACTIVE: BooleanProperty = BooleanProperty.of("active")
        val FACING: DirectionProperty = Properties.FACING
    }
}