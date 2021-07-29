package com.charlotte.sweetnotsavourymod.client.entity;

import org.antlr.v4.runtime.misc.NotNull;

import com.charlotte.sweetnotsavourymod.SweetNotSavouryMod;
import com.charlotte.sweetnotsavourymod.client.entity.model.StrawberryElfModel;
import com.charlotte.sweetnotsavourymod.common.entity.StrawberryElfEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class StrawberryElfRenderer extends MobRenderer <StrawberryElfEntity, 
StrawberryElfModel<StrawberryElfEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(SweetNotSavouryMod.MOD_ID, 
			"textures/entity/strawberryelf/strawberryelf.png");
	
	public StrawberryElfRenderer(EntityRendererManager manager) {
		
		super(manager, new StrawberryElfModel<>(), 0.1f);
		
	}
	

	@Override
	protected void preRenderCallback(@NotNull StrawberryElfEntity entitylivingbaseIn, @NotNull MatrixStack matrixStackIn,
			float partialTickTime) {
		matrixStackIn.scale(0.64F, 0.64F, 0.64F);
		
	}
	
	@Override
	public ResourceLocation getEntityTexture(StrawberryElfEntity entity) {
		return TEXTURE;
	}
	
	
}
