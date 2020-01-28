/*     */ package blackrusemod.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
		  import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ 
/*     */ public class SatelliteDaggerEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*     */   private float destY;
/*     */   private static final float DUR = 0.4F;
/*     */   private TextureAtlas.AtlasRegion img;
/*  19 */   private boolean playedSound = false;
/*  20 */   private boolean forcedAngle = false;
/*     */   
/*     */   public SatelliteDaggerEffect(float x, float y) {
/*  23 */     this.img = com.megacrit.cardcrawl.helpers.ImageMaster.DAGGER_STREAK;
/*  24 */     this.x = (x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F);
/*  25 */     this.destY = y;
/*  26 */     this.y = (this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F);
/*  27 */     this.startingDuration = 0.4F;
/*  28 */     this.duration = DUR;
/*  29 */     this.scale = Settings.scale;
/*  30 */     this.rotation = MathUtils.random(-3.0F, 3.0F);
/*  31 */     this.color = new Color(0.0F, 1.0F, 0.2F, 1.0F);
/*     */   }
/*     */   
/*     */   public SatelliteDaggerEffect(float x, float y, float fAngle) {
/*  35 */     this.img = com.megacrit.cardcrawl.helpers.ImageMaster.DAGGER_STREAK;
/*  36 */     this.x = (x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F);
/*  37 */     this.destY = y;
/*  38 */     this.y = (this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F);
/*  39 */     this.startingDuration = 0.4F;
/*  40 */     this.duration = 0.4F;
/*  41 */     this.scale = Settings.scale;
/*  42 */     this.rotation = fAngle;
/*  43 */     this.color = new Color(0.0F, 1.0F, 0.2F, 1.0F);
/*  44 */     this.forcedAngle = true;
/*     */   }
/*     */   
/*     */   private void playRandomSfX()
/*     */   {
/*  49 */     int roll = MathUtils.random(5);
/*  50 */     switch (roll) {
/*     */     case 0: 
/*  52 */       CardCrawlGame.sound.play("ATTACK_DAGGER_1");
/*  53 */       break;
/*     */     case 1: 
/*  55 */       CardCrawlGame.sound.play("ATTACK_DAGGER_2");
/*  56 */       break;
/*     */     case 2: 
/*  58 */       CardCrawlGame.sound.play("ATTACK_DAGGER_3");
/*  59 */       break;
/*     */     case 3: 
/*  61 */       CardCrawlGame.sound.play("ATTACK_DAGGER_4");
/*  62 */       break;
/*     */     case 4: 
/*  64 */       CardCrawlGame.sound.play("ATTACK_DAGGER_5");
/*  65 */       break;
/*     */     default: 
/*  67 */       CardCrawlGame.sound.play("ATTACK_DAGGER_6");
/*     */     }
/*     */   }
/*     */   
/*     */   public void update()
/*     */   {
/*  73 */     if (!this.playedSound) {
/*  74 */       playRandomSfX();
/*  75 */       this.playedSound = true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  80 */     this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
/*  81 */     if (this.duration < 0.0F) {
/*  82 */       this.isDone = true;
/*     */     }
/*  84 */     if (this.duration > 0.2F) {
/*  85 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.2F) * 5.0F);
/*     */     } else {
/*  87 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
/*     */     }
/*  89 */     this.scale = Interpolation.bounceIn.apply(Settings.scale * 0.5F, Settings.scale * 1.5F, this.duration / 0.4F);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb)
/*     */   {
/*  94 */     sb.setColor(this.color);
/*  95 */     if (!this.forcedAngle) {
/*  96 */       sb.draw(this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 108 */       sb.setBlendFunction(770, 1);
/* 109 */       sb.draw(this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 120 */       sb.setBlendFunction(770, 771);
/*     */     }
/*     */     else
/*     */     {
/* 124 */       sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */       sb.setBlendFunction(770, 1);
/* 137 */       sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 148 */       sb.setBlendFunction(770, 771);
/*     */     }
/*     */   }
/*     */
/* 152 */   public void dispose() {
/* 153 */       // Stub required in 2.0 - if this is needed, put stuff here.
/*     */   }
/*     */ }
