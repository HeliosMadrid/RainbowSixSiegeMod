package fr.helios.rainbowsixsiege.entities.models;// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelCar extends R6ModelBase
{
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;

	public ModelCar() {
		textureWidth = 128;
		textureHeight = 128;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -3.0F, -14.0F, 8, 2, 19, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 16, 49, 4.0F, -3.0F, 1.0F, 1, 3, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 49, 4.0F, -3.0F, -12.0F, 1, 3, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 49, -5.0F, -3.0F, -12.0F, 1, 3, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 42, 40, -5.0F, -3.0F, 1.0F, 1, 3, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 21, -4.0F, -5.0F, -12.0F, 8, 2, 17, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 50, 21, -4.0F, -7.0F, -6.0F, 8, 2, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 34, 40, 4.0F, -6.0F, -5.0F, 1, 4, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 40, -5.0F, -6.0F, -5.0F, 1, 4, 3, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -4.1635F, -11.2582F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2618F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 40, -3.0F, -1.0F, -0.5F, 6, 2, 7, 0.0F, false));
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