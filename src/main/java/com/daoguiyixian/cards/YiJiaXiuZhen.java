package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.MoonWindAction;
import com.daoguiyixian.action.YiJiaXiuZhenAction;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;
//对标肾上腺 0 抽2 1能---》0 消耗1 获得1能，如果是诅咒或者状态，多获得1随机无色
public class YiJiaXiuZhen extends CustomCard {
    public static final String ID = ModHelper.MakePath(YiJiaXiuZhen.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/YiJiaXiuZhen.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public YiJiaXiuZhen() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = 14;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust=true;


    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (this.upgraded) {
            this.addToBot(new YiJiaXiuZhenAction(p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.upgraded));
            this.exhaust=false;
        } else {
            this.addToBot(new YiJiaXiuZhenAction(p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.upgraded));

        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
//            this.upgradeBlock(4);
//            this.upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new YiJiaXiuZhen();
    }
}
