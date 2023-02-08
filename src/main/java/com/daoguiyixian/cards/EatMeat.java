package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.EatMeatPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//丹阳子附体-击杀回血-加攻-能量上限-1
public class EatMeat extends CustomCard {
    public static final String ID = ModHelper.MakePath(EatMeat.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/EatMeat.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.POWER;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public EatMeat() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new EatMeatPower(p, this.baseMagicNumber), this.baseMagicNumber));


        if(this.upgraded){
            //加力量和敏捷
            this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new DexterityPower(p, 10), 10));
            this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new StrengthPower(p, 10), 10));
            this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1), 1));
        }else {
            //加力量和敏捷
            this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new DexterityPower(p, 7), 7));
            this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new StrengthPower(p, 7), 7));
            this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1), 1));
        }


//        if(this.upgraded){
//            AbstractDungeon.player.gainGold(25);
//        }else {
//            AbstractDungeon.player.gainGold(20);
//        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
//            this.upgradeMagicNumber(1);

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new EatMeat();
    }


}
