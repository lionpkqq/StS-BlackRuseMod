package blackrusemod.cards;

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
	private DamageInfo info;

	public TemporalSlicing() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_SLICING), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.baseDamage = ATTACK_DMG;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
	public void triggerWhenDrawn() {
		this.info = new DamageInfo (AbstractDungeon.player, this.baseDamage, this.damageTypeForTurn);
		
		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			this.info.applyPowers(AbstractDungeon.player, mo);
			AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, this.info, AbstractGameAction.AttackEffect.NONE));
		}

		AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			this.info.applyPowers(AbstractDungeon.player, mo);
			AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, this.info, AbstractGameAction.AttackEffect.NONE));
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
}