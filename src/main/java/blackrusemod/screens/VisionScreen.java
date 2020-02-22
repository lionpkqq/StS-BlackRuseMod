package blackrusemod.screens;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.rewards.*;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.*;
import com.megacrit.cardcrawl.helpers.controller.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.helpers.input.*;

import blackrusemod.BlackRuseMod;

import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;

public class VisionScreen extends CardRewardScreen
{
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static final float PAD_X;
    private static final float CARD_TARGET_Y;
    public ArrayList<AbstractCard> rewardGroup;
    public AbstractCard codexCard;
    public boolean onCardSelect;
    public boolean hasTakenAll;
    public boolean cardOnly;
    public RewardItem rItem;
    private boolean codex;
    private boolean draft;
    private String header;
    private SkipCardButton skipButton;
    private SingingBowlButton bowlButton;
    private int draftCount;
    public AbstractCard prediction;
    
    public VisionScreen() {
        this.codexCard = null;
        this.onCardSelect = true;
        this.hasTakenAll = false;
        this.cardOnly = false;
        this.rItem = null;
        this.codex = false;
        this.draft = false;
        this.header = "";
        this.skipButton = new SkipCardButton();
        this.bowlButton = new SingingBowlButton();
        this.draftCount = 0;
    }
    
    public void update() {
        this.skipButton.update();
        this.bowlButton.update();
        this.updateControllerInput();
        this.cardSelectUpdate();
    }
    
    private void updateControllerInput() {
        if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden) {
            return;
        }
        int index = 0;
        boolean anyHovered = false;
        for (final AbstractCard c : this.rewardGroup) {
            if (c.hb.hovered) {
                anyHovered = true;
                break;
            }
            ++index;
        }
        if (!anyHovered) {
            index = 0;
            Gdx.input.setCursorPosition((int)this.rewardGroup.get(index).hb.cX, Settings.HEIGHT - (int)this.rewardGroup.get(index).hb.cY);
        }
        else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
            if (--index < 0) {
                index = this.rewardGroup.size() - 1;
            }
            Gdx.input.setCursorPosition((int)this.rewardGroup.get(index).hb.cX, Settings.HEIGHT - (int)this.rewardGroup.get(index).hb.cY);
        }
        else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
            if (++index > this.rewardGroup.size() - 1) {
                index = 0;
            }
            Gdx.input.setCursorPosition((int)this.rewardGroup.get(index).hb.cX, Settings.HEIGHT - (int)this.rewardGroup.get(index).hb.cY);
        }
    }
    
    private void cardSelectUpdate() {
        AbstractCard hoveredCard = null;
        for (final AbstractCard c : this.rewardGroup) {
            c.update();
            c.updateHoverLogic();
            if (c.hb.justHovered) {
                CardCrawlGame.sound.playV("CARD_OBTAIN", 0.4f);
            }
            if (c.hb.hovered) {
                hoveredCard = c;
            }
        }
        if (hoveredCard != null && InputHelper.justClickedLeft) {
            hoveredCard.hb.clickStarted = true;
        }
        if (hoveredCard != null && (InputHelper.justClickedRight || CInputActionSet.proceed.isJustPressed())) {
            CardCrawlGame.cardPopup.open(hoveredCard);
        }
        if (hoveredCard != null && CInputActionSet.select.isJustPressed()) {
            hoveredCard.hb.clicked = true;
        }
        if (hoveredCard != null && hoveredCard.hb.clicked) {
            hoveredCard.hb.clicked = false;
            this.skipButton.hide();
            this.bowlButton.hide();
            if (this.codex) {
                this.codexCard = hoveredCard;
            }
            else {
                this.acquireCard(hoveredCard);
            }
            this.takeReward();
            if (!this.draft || this.draftCount >= 15) {
                AbstractDungeon.closeCurrentScreen();
                this.draftCount = 0;
            }
            else {
                this.draftOpen();
            }
        }
    }
    
    private void acquireCard(final AbstractCard hoveredCard) {
        this.prediction = hoveredCard;
    }
    
    private void takeReward() {
        if (this.rItem != null) {
            AbstractDungeon.combatRewardScreen.rewards.remove(this.rItem);
            AbstractDungeon.combatRewardScreen.positionRewards();
            if (AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
                AbstractDungeon.combatRewardScreen.hasTakenAll = true;
                AbstractDungeon.overlayMenu.proceedButton.show();
            }
        }
    }
    
    public void render(final SpriteBatch sb) {
        this.skipButton.render(sb);
        this.bowlButton.render(sb);
        this.renderCardReward(sb);
    }
    
    private void renderCardReward(final SpriteBatch sb) {
        for (final AbstractCard c : this.rewardGroup) {
            c.render(sb);
        }
        for (final AbstractCard c : this.rewardGroup) {
            c.renderCardTip(sb);
        }
    }
    
    public void reopen() {
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
        if (this.draft) {
            this.skipButton.hide();
            this.bowlButton.hide();
        }
        else if (this.codex) {
            this.skipButton.show();
            this.bowlButton.hide();
        }
        else if (AbstractDungeon.player.hasRelic("Singing Bowl")) {
            this.skipButton.show(true);
            this.bowlButton.show(this.rItem);
        }
        else {
            this.skipButton.show();
            this.bowlButton.hide();
        }
        AbstractDungeon.topPanel.unhoverHitboxes();
        AbstractDungeon.isScreenUp = true;
        if (!this.codex) {
            AbstractDungeon.dynamicBanner.appear(this.header);
        }
        else {
            AbstractDungeon.dynamicBanner.appear(CardRewardScreen.TEXT[1]);
        }
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }
    
    public void open(final ArrayList<AbstractCard> cards, final RewardItem rItem, final String header) {
    	AbstractDungeon.cardRewardScreen = BlackRuseMod.vs;
        this.codex = false;
        this.draft = false;
        this.rItem = rItem;
        this.skipButton.hide();
        this.bowlButton.hide();
        this.onCardSelect = true;
        AbstractDungeon.topPanel.unhoverHitboxes();
        this.rewardGroup = cards;
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
        this.header = header;
        AbstractDungeon.dynamicBanner.appear(header);
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.showBlackScreen();
        this.placeCards(Settings.WIDTH / 2.0f, VisionScreen.CARD_TARGET_Y);
    }
    
    private void placeCards(final float x, final float y) {
        switch (this.rewardGroup.size()) {
            case 1: {
                this.rewardGroup.get(0).target_x = Settings.WIDTH / 2.0f;
                this.rewardGroup.get(0).target_y = y;
                break;
            }
            case 2: {
                this.rewardGroup.get(0).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH / 2.0f;
                this.rewardGroup.get(1).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH / 2.0f;
                this.rewardGroup.get(0).target_y = y;
                this.rewardGroup.get(1).target_y = y;
                break;
            }
            case 3: {
                this.rewardGroup.get(0).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH - VisionScreen.PAD_X;
                this.rewardGroup.get(1).target_x = Settings.WIDTH / 2.0f;
                this.rewardGroup.get(2).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH + VisionScreen.PAD_X;
                this.rewardGroup.get(0).target_y = y;
                this.rewardGroup.get(1).target_y = y;
                this.rewardGroup.get(2).target_y = y;
                break;
            }
            case 4: {
                this.rewardGroup.get(0).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH * 1.5f - VisionScreen.PAD_X * 1.5f;
                this.rewardGroup.get(1).target_x = Settings.WIDTH / 2.0f - AbstractCard.IMG_WIDTH / 2.0f - VisionScreen.PAD_X / 2.0f;
                this.rewardGroup.get(2).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH / 2.0f + VisionScreen.PAD_X / 2.0f;
                this.rewardGroup.get(3).target_x = Settings.WIDTH / 2.0f + AbstractCard.IMG_WIDTH * 1.5f + VisionScreen.PAD_X * 1.5f;
                this.rewardGroup.get(0).target_y = y;
                this.rewardGroup.get(1).target_y = y;
                this.rewardGroup.get(2).target_y = y;
                this.rewardGroup.get(3).target_y = y;
                break;
            }
        }
        for (final AbstractCard c : this.rewardGroup) {
            c.drawScale = 0.75f;
            c.targetDrawScale = 0.75f;
            c.current_x = x;
            c.current_y = y;
        }
    }
    
    public void onClose() {
        AbstractDungeon.cardRewardScreen = new CardRewardScreen();
    }
    
    public void reset() {
        this.draftCount = 0;
        this.codex = false;
        this.draft = false;
    }
    
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("CardRewardScreen");
        TEXT = VisionScreen.uiStrings.TEXT;
        PAD_X = 40.0f * Settings.scale;
        CARD_TARGET_Y = Settings.HEIGHT * 0.45f;
    }
}
