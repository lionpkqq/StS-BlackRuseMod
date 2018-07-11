package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.KnivesPower;

public class Sunlight extends CustomCard {
	public static final String ID = "Sunlight";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int ATTACK_DMG = 21;
	private static final int UPGRADE_PLUS_DMG = 9;
	public Sunlight() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SUNLIGHT), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.isMultiDamage = true;
		this.baseDamage = ATTACK_DMG;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("KnivesPower")) {
			for (int i = 0; i < Math.min(1, p.getPower("KnivesPower").amount); i++) {
				AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
				AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY), 0.1F));
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KnivesPower(p, -1), -1));
				if (p.hasPower("SurpressingFirePower")) AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower("SurpressingFirePower").amount));
			}
			if (p.getPower("KnivesPower").amount == 0) {
				if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
					AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "飞刀用光了！", 1.0F, 2.0F));
				}
				else {
					AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "I have depleted my Knives!", 1.0F, 2.0F));
				}
			}
		}
		else {
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "身上没有飞刀！", 1.0F, 2.0F));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "I don't have any Knives!", 1.0F, 2.0F));
			}
		}
	}

	public AbstractCard makeCopy() {
		return new Sunlight();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}