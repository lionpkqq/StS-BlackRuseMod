package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class MatrixPower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(MatrixPower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	
	public MatrixPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("silver_matrix48");
		this.region128 = powerAltas.findRegion("silver_matrix128");
	}
	
	@Override
	public float atDamageFinalReceive(final float damage, final DamageInfo.DamageType type) {
        return this.calculateDamageTakenAmount(damage, type);
    }
    
    private float calculateDamageTakenAmount(final float damage, final DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
            return damage * 0.5f;
        }
        return damage;
    }
	
	@Override
    public int onAttacked(final DamageInfo info, final int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
            flash();
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
        return damageAmount;
    }

	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}