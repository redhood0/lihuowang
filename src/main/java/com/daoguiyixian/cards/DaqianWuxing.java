package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.WuxingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WraithFormPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class DaqianWuxing extends CustomCard {
    public static final String ID = ModHelper.MakePath(DaqianWuxing.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/DaqianWuxing.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public DaqianWuxing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust=true;

        this.tags.add(DA_QIAN_LU);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

//        this.addToBot(new LoseHPAction(p, p, 8));
//        this.addToBot(new GainBlockAction(p, p, this.block));
//        this.addToBot(new GainEnergyAction(this.magicNumber));
//        this.addToBot(new WuxingPower(p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new WuxingPower(p, this.magicNumber), this.magicNumber));


    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
//            this.upgradeBaseCost(2);
            this.upgradeMagicNumber(1);
        }
    }


    public AbstractCard makeCopy() {
        return new DaqianWuxing();
    }

//    static {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
//    }
}
