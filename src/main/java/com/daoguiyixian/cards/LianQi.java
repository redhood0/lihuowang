package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.YiJiaXiuZhenAction;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//炼气 -被消耗-回能；使用-格挡
public class LianQi extends CustomCard {
    public static final String ID = ModHelper.MakePath(LianQi.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/LianQi.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public LianQi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 1;



    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));

//        if (this.upgraded) {
//            this.addToBot(new YiJiaXiuZhenAction(p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.upgraded));
//            this.exhaust=false;
//        } else {
//            this.addToBot(new YiJiaXiuZhenAction(p, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.upgraded));
//
//        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
//            this.upgradeMagicNumber(1);
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new LianQi();
    }

    @Override
    public void triggerOnExhaust() {
        //获得一点能量
        AbstractDungeon.player.gainEnergy(1);

    }
}
