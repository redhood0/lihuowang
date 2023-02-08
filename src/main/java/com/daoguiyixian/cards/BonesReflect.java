package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.FanShangAction;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LimitBreakAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.LimitBreak;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class BonesReflect extends CustomCard {

    public static final String ID = ModHelper.MakePath(BonesReflect.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/BonesReflect.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public BonesReflect() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust=true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if(!p.hasPower("Thorns")){
            //减去格挡
            int block = p.currentBlock;
            p.loseBlock(block,false);
            //获得刺
            int thornsNum = block/2;
            if(thornsNum > 0){
                this.addToTop(new ApplyPowerAction(p, p, new ThornsPower(p, thornsNum), thornsNum));
            }



        }else {
            this.addToBot(new FanShangAction());
        }


    }

    public AbstractCard makeCopy() {
        return new BonesReflect();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

//    static {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings("Limit Break");
//    }
}
