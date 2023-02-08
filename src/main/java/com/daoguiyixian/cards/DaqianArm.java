package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.relics.Dengjie;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class DaqianArm extends CustomCard {
    public static final String ID = ModHelper.MakePath(DaqianArm.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/DaqianArm.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    public DaqianArm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 27;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust=true;
        this.selfRetain = true;
        this.tags.add(DA_QIAN_LU);
//        this.tags.add(CardTags.STARTER_STRIKE);
//        this.tags.add(CardTags.STRIKE);
        //----------------------------
//        if(AbstractDungeon.player != null){
//            double dengjie_buff = 1;
//            if(AbstractDungeon.player.hasRelic(Dengjie.ID)){
//                int number = AbstractDungeon.player.getRelic(Dengjie.ID).counter;
//                dengjie_buff += number * 0.08;
//            }
//            int realBaseDamage  = (int)( this.baseDamage*dengjie_buff);
//            this.baseDamage = realBaseDamage;
//        }


    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(7); // 将该卡牌的伤害提高3点。
            this.upgradeMagicNumber(1);
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
//            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }


    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        if (abstractMonster != null) {
            this.addToBot(new VFXAction(new HemokinesisEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.5F));
        }


        this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, 10));
        this.addToBot(new ExhaustAction(abstractPlayer, abstractPlayer, 1, false));
        this.addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, this.magicNumber, false),  this.magicNumber));

    }

    public void calculateCardDamage(AbstractMonster mo) {


        //----------------登阶buff--------------------

//        int realBaseDamage = this.baseDamage;
//        this.baseDamage += this.magicNumber * countCards();
//        super.calculateCardDamage(mo);
//        this.baseDamage = realBaseDamage;
//        this.isDamageModified = this.damage != this.baseDamage;
    }

//    @Override
//    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
//        //----------------登阶buff--------------------
//        double dengjie_buff = 1;
//        if(AbstractDungeon.player.hasRelic(Dengjie.ID)){
//            int number = AbstractDungeon.player.getRelic(Dengjie.ID).counter;
//            dengjie_buff += number * 0.08;
//        }
//        int realBaseDamage  = (int)( this.baseDamage*dengjie_buff);
//
//        LihuowangMod.logger.error("=========realBaseDamage===========>"+realBaseDamage);
//        LihuowangMod.logger.error("=========dengjie_buff===========>"+dengjie_buff);
//
////        this.baseDamage  = realBaseDamage;
////        super.calculateCardDamage(mo);
////        this.baseDamage = realBaseDamage;
////        this.isDamageModified = this.damage != this.baseDamage;
//        return realBaseDamage;
//    }



//    @Override
//    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
//
//        double dengjie_buff = 1;
//        if(AbstractDungeon.player.hasRelic(Dengjie.ID)){
//            int number = AbstractDungeon.player.getRelic(Dengjie.ID).counter;
//            dengjie_buff += number * 0.08;
//        }
//        int realBaseDamage  = (int)( tmp * dengjie_buff);
//
//        LihuowangMod.logger.error("=========realBaseDamage===========>"+realBaseDamage);
//        LihuowangMod.logger.error("=========dengjie_buff===========>"+dengjie_buff);
//
//
////        if(this.baseDamage == realBaseDamage){
////            this.baseDamage  = realBaseDamage;
////        }
//
////        this.baseDamage = realBaseDamage;
//        this.isDamageModified = true;
//
//        return super.calculateModifiedCardDamage(player, mo, realBaseDamage);
//    }



//        @Override
//    public float calculateModifiedCardDamage(AbstractPlayer player, float tmp) {
//
//            double dengjie_buff = 1;
//            if(AbstractDungeon.player.hasRelic(Dengjie.ID)){
//                int number = AbstractDungeon.player.getRelic(Dengjie.ID).counter;
//                dengjie_buff += number * 0.08;
//            }
//            int realBaseDamage  = (int)( tmp * dengjie_buff);
//
//            LihuowangMod.logger.error("=========realBaseDamage===========>"+realBaseDamage);
//            LihuowangMod.logger.error("=========dengjie_buff===========>"+dengjie_buff);
//
//
////        if(this.baseDamage == realBaseDamage){
////            this.baseDamage  = realBaseDamage;
////        }
//
////        this.baseDamage = realBaseDamage;
//            this.isDamageModified = true;
//        return super.calculateModifiedCardDamage(player, realBaseDamage);
//    }

    @Override
    public AbstractCard makeCopy() {
        return new DaqianArm();
    }
}
