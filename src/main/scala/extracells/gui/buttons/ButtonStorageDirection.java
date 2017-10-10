package extracells.gui.buttons;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

import org.lwjgl.opengl.GL11;

import appeng.api.config.AccessRestriction;
import extracells.gui.IToolTipProvider;

public class ButtonStorageDirection extends GuiButton implements IToolTipProvider {

	private AccessRestriction access;

	public ButtonStorageDirection(int ID, int xPos, int yPos, int width,
		int height, AccessRestriction mode) {
		super(ID, xPos, yPos, width, height, "");
		this.access = mode;
	}

	@Override
	public void drawButton(Minecraft minecraftInstance, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mouseDragged(minecraftInstance, x, y);

		minecraftInstance.getTextureManager().bindTexture(new ResourceLocation("appliedenergistics2", "textures/guis/states.png"));
		drawTexturedModalRect(this.xPosition, this.yPosition, 240, 240, 16, 16);

		switch (this.access) {
			case NO_ACCESS:
				drawTexturedModalRect(this.xPosition, this.yPosition, 96, 0, 16, 16);
				break;
			case READ:
				drawTexturedModalRect(this.xPosition, this.yPosition, 16, 144, 16,
					16);
				break;
			case READ_WRITE:
				drawTexturedModalRect(this.xPosition, this.yPosition, 32, 144, 16,
					16);
				break;
			case WRITE:
				drawTexturedModalRect(this.xPosition, this.yPosition, 0, 144, 16,
					16);
				break;
			default:
				break;
		}
	}

	@Override
	public boolean isMouseOver(int mouseX, int mouseY) {
		return isMouseOver();
	}

	@Override
	public List<String> getToolTip(int mouseX, int mouseY) {
		List<String> description = new ArrayList<String>();
		description.add(I18n.translateToLocal("gui.tooltips.appliedenergistics2.IOMode"));
		String explanation = "";
		switch (this.access) {
			case NO_ACCESS:
				explanation = I18n.translateToLocal("gui.tooltips.appliedenergistics2.Disabled");
				break;
			case READ:
				explanation = I18n.translateToLocal("gui.tooltips.appliedenergistics2.Read");
				break;
			case READ_WRITE:
				explanation = I18n.translateToLocal("gui.tooltips.appliedenergistics2.ReadWrite");
				break;
			case WRITE:
				explanation = I18n.translateToLocal("gui.tooltips.appliedenergistics2.Write");
				break;
			default:
				break;
		}

		for (String current : Splitter.fixedLength(30).split(explanation)) {
			description.add(TextFormatting.GRAY + current);
		}
		return description;
	}

	public AccessRestriction getAccessRestriction() {
		return this.access;
	}

	public void setAccessRestriction(AccessRestriction mode) {
		this.access = mode;
	}
}