package blackrusemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.UpdateCardDescriptionAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class Potential extends AbstractShiftCard {
	public static final String ID = "BlackRuseMod:Potential";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int ATTACK_DMG_GROW = 5;

	public Potential() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.POTENTIAL), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = ATTACK_DMG_GROW;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AttackEffect effect;
		if (this.damage < 14)
			effect = AttackEffect.SMASH;
		else if (this.damage >= 14 && this.damage < 28)
			effect = AttackEffect.BLUNT_LIGHT;
		else if (this.damage >= 28 && this.damage < 42)
			effect = AttackEffect.BLUNT_HEAVY;
		else {
			if (m != null) {
				addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
			}
			for(int i=0;i<8;i++) addToBot(new WaitAction(0.1F));
			effect = AttackEffect.BLUNT_HEAVY;
		}
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), effect));
	}
	
	@Override
	public void triggerShift() {
		this.baseDamage += this.magicNumber;
		this.superFlash();
		addToTop(new DiscardToHandAction(this));
		addToTop(new WaitAction(0.3F));
		addToBot(new UpdateCardDescriptionAction(this));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Potential();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_PLUS_DMG);
		}
	}
}