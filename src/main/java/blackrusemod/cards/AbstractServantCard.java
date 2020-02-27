package blackrusemod.cards;

import basemod.abstracts.CustomCard;
import blackrusemod.characters.TheServant;
import blackrusemod.powers.ElegancePower;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;

public abstract class AbstractServantCard extends CustomCard {

	public static class Enums {
        @SpireEnum(name = "SERVANT_TEMP")
        public static AbstractCard.CardTags TEMP;
        @SpireEnum(name = "SERVANT_SILVER_BLADES")
        public static AbstractCard.CardTags SILVER_BLADES;
    }

	protected CardStrings strings;
	public int baseProtection;
	public int protection;
	public boolean isProtectionModified;
	public boolean protectionUpgraded;

	public AbstractServantCard(String id, String texture, int cost, CardType type, CardRarity rarity, CardTarget target) {
		super(id, languagePack.getCardStrings(id).NAME, texture, cost, languagePack.getCardStrings(id).DESCRIPTION, type, TheServant.Enums.COLOR_SILVER, rarity, target);
		this.strings = languagePack.getCardStrings(id);
    }
    
    @Override
    public void applyPowers() {
        super.applyPowers();
        this.protection = this.baseProtection;
        if(AbstractDungeon.player.hasPower(ElegancePower.POWER_ID)) {
            this.protection += AbstractDungeon.player.getPower(ElegancePower.POWER_ID).amount;
        }
        this.isProtectionModified = this.protection != this.baseProtection;
    }

	@Override
	public void displayUpgrades() {
        super.displayUpgrades();
        if (this.protectionUpgraded) {
            this.protection = this.baseProtection;
            this.isProtectionModified = true;
        }
    }

    public void upgradeProtection(int amount) {
        this.protection = this.baseProtection += amount;
        this.protectionUpgraded = true;
    }
}