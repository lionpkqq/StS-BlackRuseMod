package blackrusemod.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import blackrusemod.BlackRuseMod;

public class ShatteredReality extends AbstractShiftCard {
	public static final String ID = BlackRuseMod.makeID(ShatteredReality.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("shattered_reality.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 3;

	public ShatteredReality() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
	}
	
	@Override
	public void triggerShift() {
		addToBot(new SFXAction("THUNDERCLAP", 0.05F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!mo.isDeadOrEscaped()) {
				addToBot(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
				addToBot(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, damageCalculation(AbstractDungeon.player, mo, this.baseDamage)*2, this.damageTypeForTurn), AttackEffect.NONE));
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new ShatteredReality();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
	
	public int damageCalculation(AbstractPlayer player, AbstractMonster monster, int damage) {
		float tmp = damage;
		for (AbstractPower p : player.powers) tmp = p.atDamageGive(tmp, this.damageTypeForTurn);
		for (AbstractPower p : monster.powers) tmp = p.atDamageReceive(tmp, this.damageTypeForTurn);
		for (AbstractPower p : player.powers) tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn);
		for (AbstractPower p : monster.powers) tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn);
		int output = MathUtils.floor(tmp);
		if (output < 0) output = 0;
		return output;
	}
}