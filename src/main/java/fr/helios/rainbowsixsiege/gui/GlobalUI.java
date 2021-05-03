package fr.helios.rainbowsixsiege.gui;

import fr.helios.rainbowsixsiege.gui.button.ButtonHoverable;
import fr.helios.rainbowsixsiege.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.client.renderer.GlStateManager.*;

public class GlobalUI extends GuiScreen
{
    private static final ResourceLocation globalGUITexture = new ResourceLocation(References.MODID, "textures/gui/global_menu.png");

    static final Map<EntityPlayerSP, GlobalUI> globalsUi = new HashMap<>();

    int xSize = 247, ySize = 165, x, y;

    final EntityPlayerSP player;

    public GlobalUI(EntityPlayerSP player)
    {
        this.player = player;
        globalsUi.put(this.player, this);
    }

    @Override public void initGui()
    {
        x = (width - xSize) / 2;
        y = (height - ySize) / 2;

        setupButtons();
    }

    @Override public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        drawImages();
        drawPlayer(x + 135, y + 149, 50, Minecraft.getMinecraft().player, (x + 135) - mouseX, (y + 73) - mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawImages() {
        mc.getTextureManager().bindTexture(globalGUITexture);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    private void drawPlayer(float x, float y, float scale, EntityPlayerSP player, float mouseX, float mouseY) {
        enableColorMaterial();
        pushMatrix();
        translate(x, y, 50.0f);
        scale(-scale, scale, scale);
        rotate(180.0f, 0.0f, 0.0f, 1.0f);

        float pOffset = player.renderYawOffset,
              pRot = player.rotationYaw,
              pRotPitch = player.rotationPitch,
              pPrevRotYawHead = player.prevRotationYawHead,
              pRotYawHead = player.rotationYawHead;


        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);

        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);

        player.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        player.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        player.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        player.rotationYawHead = player.rotationYaw;
        player.prevRotationYawHead = player.rotationYaw;

        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);

        player.renderYawOffset = pOffset;
        player.rotationYaw = pRot;
        player.rotationPitch = pRotPitch;
        player.prevRotationYawHead = pPrevRotYawHead;
        player.rotationYawHead = pRotYawHead;

        popMatrix();
        RenderHelper.disableStandardItemLighting();
        setActiveTexture(OpenGlHelper.lightmapTexUnit);
        disableTexture2D();
        setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override protected void actionPerformed(GuiButton button)
    {
        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().player.sendChatMessage("Play");
                break;
            case 1:
                Minecraft.getMinecraft().player.sendChatMessage("Agents");
                break;
        }
    }

    private void setupButtons() {
        buttonList.add(new ButtonHoverable(0, x + 9, y + 51, 9, 170, 64, 21, globalGUITexture));
        buttonList.add(new ButtonHoverable(1, x + 9, y + 91, 76, 170, 64, 21, globalGUITexture));
    }

    public static void displayGui(EntityPlayerSP player) {
        if(globalsUi.containsKey(player)) {
            Minecraft.getMinecraft().displayGuiScreen(globalsUi.get(player));
        } else Minecraft.getMinecraft().displayGuiScreen(new GlobalUI(player));
    }
}
