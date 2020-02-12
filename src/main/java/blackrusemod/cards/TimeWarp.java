package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class TimeWarp extends AbstractShiftCard {
	public static final String ID = "BlackRuseMod:TimeWarp";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int DISCARD_AND_DRAW = 1;

	public TimeWarp() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TIME_WARP), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DISCARD_AND_DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if ((p != null) && (m != null)) 
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 
				AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
	}
	
	public void triggerShift() {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
	}

	public AbstractCard makeCopy() {
		return new TimeWarp();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
			upgradeMagicNumber(1);
		}
	}
}