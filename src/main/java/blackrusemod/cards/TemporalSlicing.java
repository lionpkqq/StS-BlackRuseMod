package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.TemporalDamageAction;
import blackrusemod.patches.AbstractCardEnum;

public class TemporalSlicing extends CustomCard {
	public static final String ID = "BlackRuseMod:TemporalSlicing";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 4;
	private static final int ATTACK_TIMES = 2;

	public TemporalSlicing() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_SLICING), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.isEthereal = true;
		this.exhaust = true;
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = ATTACK_TIMES;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++) {
			addToBot(new SFXAction("ATTACK_HEAVY"));
			addToBot(new VFXAction(p, new CleaveEffect(), 0.3F));
			addToBot(new TemporalDamageAction(this.baseDamage));
		}
	}

	public AbstractCard makeCopy() {
		return new TemporalSlicing();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}