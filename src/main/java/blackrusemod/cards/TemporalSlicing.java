package blackrusemod.cards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class TemporalSlicing extends CustomCard {
	public static final String ID = "TemporalSlicing";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int COST = -2;
	public static final Logger logger = LogManager.getLogger(BlackRuseMod.class.getName());

	public TemporalSlicing() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_SLICING), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.baseDamage = ATTACK_DMG;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(AbstractDungeon.player,
						damageCalculation(AbstractDungeon.player, mo, this.baseDamage), this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.NONE));
			}
		}

		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if ((mo != null) && (!mo.isDeadOrEscaped())) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(AbstractDungeon.player,
						damageCalculation(AbstractDungeon.player, mo, this.baseDamage), this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.NONE));
			}
		}
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		return false;
	}

	public AbstractCard makeCopy() {
		return new TemporalSlicing();
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