package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionPowerAction;
import blackrusemod.patches.VisionApplyPatch;

public abstract class AbstractVisionPower extends AbstractPower {
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	public boolean prediction;
	private static int idOffset;
	
	public AbstractVisionPower(String name, String id, String texture, AbstractMonster target, int amount, boolean prediction) {
		this.name = name;
		this.ID = (id + idOffset);
		idOffset += 1;
		this.amount = amount;
		this.owner = target;
		this.amount = amount;
		this.prediction = prediction;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.region48 = powerAltas.findRegion(texture + "48");
		this.region128 = powerAltas.findRegion(texture + "128");
	}
	
	public void atEndOfTurn(boolean isPlayer) {
		VisionApplyPatch.visionActions.add(new VisionPowerAction(this));
	}
	
	public void onVision(boolean result) {}
}