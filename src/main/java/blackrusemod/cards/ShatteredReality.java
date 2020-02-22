package blackrusemod.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class ShatteredReality extends AbstractShiftCard {
	public static final String ID = "BlackRuseMod:ShatteredReality";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 3;

	public ShatteredReality() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SHATTERED_REALITY), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}
	
	public void triggerShift() {
		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!mo.isDeadOrEscaped()) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(AbstractDungeon.player,
						damageCalculation(AbstractDungeon.player, mo, this.baseDamage)*2, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.NONE));
			}
		}
	}

	public AbstractCard makeCopy() {
		return new ShatteredReality();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
	
	public int damageCalculation(AbstractPlayer player, AbstractMonster monster, int damage) {
		float tmp = damage;
		for (AbstractPower p : player.powers) tmp = p.atDamageGive(tmp, DamageInfo.DamageType.NORMAL);
		for (AbstractPower p : monster.powers) tmp = p.atDamageReceive(tmp, DamageInfo.DamageType.NORMAL);
		for (AbstractPower p : player.powers) tmp = p.atDamageFinalGive(tmp, DamageInfo.DamageType.NORMAL);
		for (AbstractPower p : monster.powers) tmp = p.atDamageFinalReceive(tmp, DamageInfo.DamageType.NORMAL);
		int output = MathUtils.floor(tmp);
		if (output < 0) output = 0;
		return output;
	}
}