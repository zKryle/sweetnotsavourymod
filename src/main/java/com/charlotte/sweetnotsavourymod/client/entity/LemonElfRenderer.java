package com.charlotte.sweetnotsavourymod.client.entity;

import org.antlr.v4.runtime.misc.NotNull;

import com.charlotte.sweetnotsavourymod.SweetNotSavouryMod;
import com.charlotte.sweetnotsavourymod.client.entity.model.LemonElfModel;
import com.charlotte.sweetnotsavourymod.common.entity.LemonElfEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LemonElfRenderer extends MobRenderer <LemonElfEntity, 
LemonElfModel<LemonElfEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(SweetNotSavouryMod.MOD_ID, 
			"textures/entity/lemonelf/lemonelf.png");
	
	public LemonElfRenderer(EntityRendererManager manager) {
		
		super(manager, new LemonElfModel<>(), 0.1f);
		
	}
	

	@Override
	protected void preRenderCallback(@NotNull LemonElfEntity entitylivingbaseIn, @NotNull MatrixStack matrixStackIn,
			float partialTickTime) {
		matrixStackIn.scale(0.64F, 0.64F, 0.64F);
		
	}
	
	@Override
	public ResourceLocation getEntityTexture(LemonElfEntity entity) {
		return TEXTURE;
	}
	
	
}

