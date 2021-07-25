package com.charlotte.sweetnotsavourymod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PoisonOakMiniDoor extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	   public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	   public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	   
	   protected static final VoxelShape SOUTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
	   protected static final VoxelShape NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
	   protected static final VoxelShape WEST_AABB = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	   protected static final VoxelShape EAST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D); 
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {	
		
	
		Boolean flag = state.get(OPEN);
		openDoor(worldIn, state, pos, flag ? false : true);
		return ActionResultType.SUCCESS;
}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
	
		super.fillStateContainer(builder);
		
		builder.add(FACING, OPEN, POWERED, HINGE);
}
	


	

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		BlockPos blockpos = context.getPos();
		World world = context.getWorld();
		         boolean flag = world.isBlockPowered(blockpos);
		         return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing())
                         .with(HINGE, this.getHingeSide(context)).with(POWERED, Boolean.valueOf(flag)).with(OPEN, false);
		
}
	

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {

		super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
		
		 boolean flag = worldIn.isBlockPowered(pos);
	      if (blockIn != this && flag != state.get(POWERED)) {
	         if (flag != state.get(OPEN)) {
	         }

	         worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(flag)).with(OPEN, Boolean.valueOf(flag)), 2);
	      } 
		
}
	
	 public void openDoor(World worldIn, BlockState state, BlockPos pos, boolean open) {
	      if (state.isIn(this) && state.get(OPEN) != open) {
	         worldIn.setBlockState(pos, state.with(OPEN, Boolean.valueOf(open)), 10);
	         worldIn.playSound(null, pos, open ? SoundEvents.BLOCK_WOODEN_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0f, 1.0f); 
	     
	      }
	   }
	 
	 public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE; 
	 
	 private DoorHingeSide getHingeSide(BlockItemUseContext context) {
	      IBlockReader iblockreader = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      net.minecraft.util.Direction direction = context.getPlacementHorizontalFacing();
	      BlockPos blockpos1 = blockpos.up();
	      net.minecraft.util.Direction direction1 = direction.rotateYCCW();
	      BlockPos blockpos2 = blockpos.offset(direction1);
	      BlockState blockstate = iblockreader.getBlockState(blockpos2);
	      BlockPos blockpos3 = blockpos1.offset(direction1);
	      BlockState blockstate1 = iblockreader.getBlockState(blockpos3);
	      net.minecraft.util.Direction direction2 = direction.rotateY();
	      BlockPos blockpos4 = blockpos.offset(direction2);
	      BlockState blockstate2 = iblockreader.getBlockState(blockpos4);
	      BlockPos blockpos5 = blockpos1.offset(direction2);
	      BlockState blockstate3 = iblockreader.getBlockState(blockpos5);
	      int i = (blockstate.hasOpaqueCollisionShape(iblockreader, blockpos2) ? -1 : 0) + 
	    		  (blockstate1.hasOpaqueCollisionShape(iblockreader, blockpos3) ? -1 : 0) + (blockstate2.hasOpaqueCollisionShape
	    				  (iblockreader, blockpos4) ? 1 : 0) + (blockstate3.hasOpaqueCollisionShape(iblockreader, blockpos5) ? 1 : 0);
	   
	            int j = direction.getXOffset();
	            int k = direction.getZOffset();
	            Vector3d vector3d = context.getHitVec();
	            double d0 = vector3d.x - (double)blockpos.getX();
	            double d1 = vector3d.z - (double)blockpos.getZ();
	           // return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && 
	            		//(k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
	            
	            return DoorHingeSide.LEFT;
	        
	   } 
	
	 
	 @Override
	 public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		 net.minecraft.util.Direction direction = state.get(FACING);
	      boolean flag = !state.get(OPEN);
	      boolean flag1 = state.get(HINGE) == DoorHingeSide.RIGHT;
	      switch(direction) {
	      case EAST:
	      default:
	         return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
	      case SOUTH:
	         return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
	      case WEST:
	         return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
	      case NORTH:
	         return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
	      }
	   }
	 

	 
	 
	public PoisonOakMiniDoor(Properties properties) {
		super(properties);
		
		
	
}
}
