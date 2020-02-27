package blackrusemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DoubleProtectionAction;
import blackrusemod.powers.ProtectionPower;

public class DualDimension extends AbstractShiftCard {
	public static final String ID = BlackRuseMod.makeID(DualDimension.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("dual_dimension.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;
	private static final int PROTECTION = 6;

	public DualDimension() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.protection = this.baseProtection = PROTECTION;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VFXAction(new VerticalAuraEffect(new Color(0.0F, 1.0F, 1.0F, 0.0F), p.hb.cX, p.hb.cY)));
		addToBot(new DoubleProtectionAction(p));
	}
	
	@Override
	public void triggerShift() {
		this.applyPowers();
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ProtectionPower(AbstractDungeon.player, this.protection), this.protection));
	}

	@Override
	public AbstractCard makeCopy() {
		return new DualDimension();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}