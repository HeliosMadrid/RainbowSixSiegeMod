package fr.helios.rainbowsixsiege.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonHoverable extends GuiButton
{
    private final ResourceLocation texture;
    final int u, v;

    public ButtonHoverable(int buttonId, int x, int y, int u, int v, int width, int height, ResourceLocation texture)
    {
        super(buttonId, x, y, width, height, "");
        this.texture = texture;
        this.u = u;
        this.v = v;
    }

    @Override public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        mc.getTextureManager().bindTexture(texture);

        boolean mouseDragged = mouseX >= this.x && mouseY >= this.y && mouseX < (this.x + this.width) && mouseY < (this.y + this.height);
        this.mouseDragged(mc, mouseX, mouseY);
        int mouseState = this.getHoverState(mouseDragged);

        this.drawTexturedModalRect(x, y, u, v + ((mouseState - 1) * height), width, height);
    }
}
