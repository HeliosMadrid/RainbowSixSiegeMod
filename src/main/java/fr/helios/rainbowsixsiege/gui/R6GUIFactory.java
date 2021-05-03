package fr.helios.rainbowsixsiege.gui;

import fr.helios.rainbowsixsiege.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Set;

public class R6GUIFactory implements IModGuiFactory
{
    @Override public void initialize(Minecraft minecraftInstance)
    {

    }

    @Override public boolean hasConfigGui()
    {
        return true;
    }

    @Override public GuiScreen createConfigGui(GuiScreen parentScreen)
    {
        return new GuiConfig(parentScreen, References.MODID, References.NAME);
    }

    @Override public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }
}
