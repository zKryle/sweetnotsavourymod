package com.charlotte.sweetnotsavourymod.common.tileentities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public abstract class BaseBedTeRenderer extends TileEntityRenderer<TileEntity>{

    protected TileEntity te;
    protected IBakedModel headbedmodel;
    protected IBakedModel bottombedmodel;

    public BaseBedTeRenderer( TileEntityRendererDispatcher rendererDispatcherIn ){
        super( rendererDispatcherIn );
    }

    protected abstract void setTileEntityAndModels(TileEntity te);

    @Override
    public void render( TileEntity tileentity , float partialTicks , MatrixStack matrixStackIn , IRenderTypeBuffer bufferIn , int combinedLightIn , int combinedOverlayIn ){
       setTileEntityAndModels(tileentity);
        World level = te.getWorld();
        BlockState state = te.getBlockState();
        BlockPos pos = te.getPos();
        switch((te.getBlockState().get( BlockStateProperties.HORIZONTAL_FACING ))){
            case NORTH:
                int zn = pos.getZ() + 1;
                if (state.get( BedBlock.PART) == BedPart.FOOT ){
                    renderBottomBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 180 , -1 , -1 );
                }
                if (state.get( BedBlock.PART) == BedPart.HEAD ){
                    renderHeadBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 180 , -1 , -1 );
                }
                break;
            case SOUTH:
                int zs = pos.getZ() - 1;
                if (state.get( BedBlock.PART) == BedPart.FOOT ){
                    renderBottomBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 0 , 0 , 0 );
                }
                if (state.get( BedBlock.PART) == BedPart.HEAD ){
                    renderHeadBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 0 , 0 , 0 );
                }
                break;
            case WEST:
                int xw = pos.getX() + 1;
                if (state.get( BedBlock.PART) == BedPart.FOOT ){
                    renderBottomBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 270 , 0 , -1 );
                }
                if (state.get( BedBlock.PART) == BedPart.HEAD ){
                    renderHeadBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 270 , 0 , -1 );
                }
                break;
            case EAST:
                int xe = pos.getX() - 1;
                if (state.get( BedBlock.PART) == BedPart.FOOT ){
                    renderBottomBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 90 , -1 , 0 );
                }
                if (state.get( BedBlock.PART) == BedPart.HEAD ){
                    renderHeadBedCorrectly( level , pos , state , matrixStackIn , te , bufferIn , 90 , -1 , 0 );
                }
                break;
        }
    }

    private void renderBottomBedCorrectly(World level, BlockPos pos, BlockState state, MatrixStack matrixStackIn, TileEntity te, IRenderTypeBuffer bufferIn, int rotation,
                                    float tx, float tz){
            RenderType renderType = RenderTypeLookup.func_239220_a_( state , false );
            matrixStackIn.push();
            matrixStackIn.rotate( Vector3f.YP.rotationDegrees(rotation) );
            matrixStackIn.translate( tx, 0, tz );
            Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelRenderer().renderModelFlat( level , bottombedmodel , state , pos , matrixStackIn , bufferIn.getBuffer( renderType ) , false , level.rand , state.getPositionRandom( pos ) , OverlayTexture.NO_OVERLAY ,
                    net.minecraftforge.client.model.data.EmptyModelData.INSTANCE );
            matrixStackIn.pop();

    }

    private void renderHeadBedCorrectly(World level, BlockPos pos, BlockState state, MatrixStack matrixStackIn, TileEntity te, IRenderTypeBuffer bufferIn, int rotation,
                                          float tx, float tz){
        RenderType renderType = RenderTypeLookup.func_239220_a_( state , false );
        matrixStackIn.push();
        matrixStackIn.rotate( Vector3f.YP.rotationDegrees(rotation) );
        matrixStackIn.translate( tx, 0, tz );
        Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelRenderer().renderModelFlat( level , headbedmodel , state , pos , matrixStackIn , bufferIn.getBuffer( renderType ) , false , level.rand , state.getPositionRandom( pos ) , OverlayTexture.NO_OVERLAY ,
                net.minecraftforge.client.model.data.EmptyModelData.INSTANCE );
        matrixStackIn.pop();

    }

    @Override
    public boolean isGlobalRenderer( TileEntity te ){
            return true;
    }
}
