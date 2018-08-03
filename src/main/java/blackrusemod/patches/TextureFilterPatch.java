package blackrusemod.patches;

import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.screens.charSelect.*;
import com.megacrit.cardcrawl.core.*;
import java.lang.reflect.*;
import basemod.abstracts.*;

public class TextureFilterPatch
{
    @SpirePatch(cls = "basemod.abstracts.CustomPlayer", method = "buildCustomOrb")
    public static class buildCustomOrbFix
    {
        @SpireInsertPatch(rloc = 5, localvars = { "energyLayers", "orbVfx" })
        public static void Insert(final CustomPlayer _inst, final String[] orbTextures, final String orbVfxPath, final ArrayList<Texture> energyLayers, final Texture orbVfx) {
            for (final Texture t : energyLayers) {
                t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            orbVfx.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
    
    @SpirePatch(cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method = "updateHitbox")
    public static class UpdateHitboxBgImgFix
    {
        @SpireInsertPatch(rloc = 40)
        public static void Insert(final CharacterOption _inst) {
            CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
    
    @SpirePatch(cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method = "ctor", paramtypes = { "java.lang.String", "com.megacrit.cardcrawl.characters.AbstractPlayer$PlayerClass", "java.lang.String", "java.lang.String" })
    public static class ButtonImageFix
    {
        @SpireInsertPatch(rloc = 32)
        public static void Insert(final Object __obj_instance, final String optionName, final Object cObj, final String buttonUrl, final String portraiImg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
            final Field buttonImgField = CharacterOption.class.getDeclaredField("buttonImg");
            buttonImgField.setAccessible(true);
            ((Texture)buttonImgField.get(__obj_instance)).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
    
    @SpirePatch(cls = "basemod.abstracts.CustomCard", method = "loadTextureFromString")
    public static class loadTextureFromStringFix
    {
        public static void PostFix(final String textureString) {
            final Texture t = CustomCard.imgMap.get(textureString);
            if (t == null) {
                return;
            }
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            CustomCard.imgMap.put(textureString, t);
        }
    }
    
    @SpirePatch(cls = "basemod.abstracts.CustomCard", method = "getPortraitImage")
    public static class getPortraitImageFix
    {
        @SpireInsertPatch(rloc = 13, localvars = { "portraitTexture" })
        public static void Insert(final CustomCard card, final Texture portraitTexture) {
            if (portraitTexture == null) {
                return;
            }
            portraitTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
    
    @SpirePatch(cls = "basemod.BaseMod", method = "saveEnergyOrbTexture")
    public static class saveEnergyOrbFix
    {
        @SpireInsertPatch(rloc = 0)
        public static void Insert(final String color, final Texture tex) {
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
    
    @SpirePatch(cls = "basemod.BaseMod", method = "saveEnergyOrbPortraitTexture")
    public static class saveEnergyOrbPortraitFix
    {
        @SpireInsertPatch(rloc = 0)
        public static void Insert(final String color, final Texture tex) {
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
}
