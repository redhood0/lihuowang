package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.daoguiyixian.action.MoonWindAction;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//2费-aoe16-击杀-回复能量-抽卡(疯癫下无法使用)
public class MoonWindSword extends CustomCard {
    public static final String ID = ModHelper.MakePath(MoonWindSword.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/MoonWindSword.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    public MoonWindSword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 11;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

//        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);

    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(5); // 将该卡牌的伤害提高3点。
            this.upgradeMagicNumber(1);
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
//            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }


    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(CrazyPower.POWER_ID)){
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new MoonWindAction(p,m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new MoonWindSword();
    }

}
