package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.BlackTaiSuiPower;
import com.daoguiyixian.power.WangLiangBodyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//魍魉之躯-抽到3诅咒-加1攻击
public class WangLiangBody extends CustomCard {
    public static final String ID = ModHelper.MakePath(WangLiangBody.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/WangLiangBody.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    public WangLiangBody() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
//            this.upgradeBaseCost(1);
            this.upgradeMagicNumber(1);
//            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();

        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        if (abstractMonster != null) {
//            this.addToBot(new VFXAction(new HemokinesisEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.5F));
//        }
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new WangLiangBodyPower(abstractPlayer, 1,this.upgraded), 1));


    }

    public AbstractCard makeCopy() {
        return new WangLiangBody();
    }

}
