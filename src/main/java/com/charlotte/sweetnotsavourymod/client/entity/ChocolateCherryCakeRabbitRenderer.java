package com.charlotte.sweetnotsavourymod.client.entity;

import org.antlr.v4.runtime.misc.NotNull;

import com.charlotte.sweetnotsavourymod.SweetNotSavouryMod;
import com.charlotte.sweetnotsavourymod.client.entity.model.ChocolateCherryCakeRabbitModel;
import com.charlotte.sweetnotsavourymod.common.entity.ChocolateCherryCakeRabbitEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ChocolateCherryCakeRabbitRenderer extends MobRenderer <ChocolateCherryCakeRabbitEntity, 
ChocolateCherryCakeRabbitModel<ChocolateCherryCakeRabbitEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(SweetNotSavouryMod.MOD_ID, 
			"textures/entity/chocolatecherrycakerabbit/chocolatecherrycakerabbit.png");
	
	public ChocolateCherryCakeRabbitRenderer(EntityRendererManager manager) {
		
		super(manager, new ChocolateCherryCakeRabbitModel<>(), 0.3f);
		
	}
	

	@Override
	protected void preRenderCallback(@NotNull ChocolateCherryCakeRabbitEntity entitylivingbaseIn, @NotNull MatrixStack matrixStackIn,
			float partialTickTime) {
		matrixStackIn.scale(0.45F, 0.45F, 0.45F);
		
	}
	
	@Override
	public ResourceLocation getEntityTexture(ChocolateCherryCakeRabbitEntity entity) {
		return TEXTURE;
	}
	
	
}
