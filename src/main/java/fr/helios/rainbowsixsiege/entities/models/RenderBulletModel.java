package fr.helios.rainbowsixsiege.entities.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class RenderBulletModel
{
	public static class BulletModel extends R6ModelBase {
		private final ModelRenderer bb_main;

		public BulletModel() {
			textureWidth = 16;
			textureHeight = 16;

			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F, false));
		}

		@Override public void doRender()
		{
			bb_main.render(0.0625f);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}

	public static class RocketModel extends R6ModelBase
	{
		private final ModelRenderer bb_main;

		public RocketModel() {
			textureWidth = 64;
			textureHeight = 64;

			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.0F, 0.0F, 7, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.25F, -0.25F, 5, 1, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.25F, 1.0F, 5, 1, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.0F, -0.5F, 5, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.0F, 1.25F, 5, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -0.75F, 0.0F, 7, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.25F, 0.0F, 5, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -0.5F, 0.0F, 5, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.5F, 0.0F, 3, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -0.25F, 0.0F, 3, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -1.75F, 0.0F, 2, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, 0.0F, 0.0F, 2, 0, 1, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 5, 45, -8.25F, -1.0F, 0.25F, 7, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.875F, -1.25F, 0.75F, 2, 1, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.875F, -1.25F, 0.0F, 2, 1, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.625F, -1.0F, 1.0F, 1, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.625F, -1.0F, -0.25F, 1, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -3.0F, -1.25F, 0.25F, 2, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -3.0F, -0.5F, 0.25F, 2, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.5F, -1.5F, 0.25F, 1, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.5F, -0.25F, 0.25F, 1, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.75F, -1.75F, 0.25F, 0, 0, 0, 0.0F, false));
			bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.75F, 0.0F, 0.25F, 0, 0, 0, 0.0F, false));
		}

		@Override public void doRender()
		{
			bb_main.render(0.0625f);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}