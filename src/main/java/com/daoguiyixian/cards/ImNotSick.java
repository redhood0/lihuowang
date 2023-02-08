package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.ReadMindAction;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//我没病-->疯狂Madness
public class ImNotSick extends CustomCard {
    public static final String ID = ModHelper.MakePath(ImNotSick.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/ImNotSick.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    public ImNotSick() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
//        this.block=this.baseBlock=5;
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Madness();

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ReadMindAction(this.block,this.magicNumber, m));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            AbstractCard c1 = new Madness();
            c1.upgrade();
            this.cardsToPreview = c1;
//            this.upgradeBlock(3);
//            //保留
//            this.retain=true;
//            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void triggerOnExhaust() {
        if(this.magicNumber == 1){
            //加一张疯狂在手牌
            this.addToBot(new MakeTempCardInHandAction(new Madness(), 1, false));
            this.addToBot(new MakeTempCardInDrawPileAction(new ImNotSick(),1,true,true));
        }else {
            AbstractCard c1 = new Madness();
            c1.upgrade();
            AbstractCard c2 = new ImNotSick();
            c2.upgrade();
            this.addToBot(new MakeTempCardInHandAction(c1, 1, false));
            this.addToBot(new MakeTempCardInDrawPileAction(c2,1,true,true));

        }


        //加入卡组

        LihuowangMod.logger.error("==========triggerOnExhaust===========>");
    }

    public AbstractCard makeCopy() {
        return new ImNotSick();
    }
}
