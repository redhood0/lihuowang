package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

//获得2张虚空,高额aoe 25伤(真伤)
public class BreakSpace extends CustomCard {
    public static final String ID = ModHelper.MakePath(BreakSpace.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/BreakSpace.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;
    public BreakSpace() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 30;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
//        this.exhaust=true;
//        this.selfRetain = true;
//        this.tags.add(DA_QIAN_LU);
//        this.tags.add(CardTags.STARTER_STRIKE);
//        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(10); // 将该卡牌的伤害提高5点。

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
//            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }


    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        if (abstractMonster != null) {
//            this.addToBot(new VFXAction(new HemokinesisEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.5F));
//        }
//
//
//        this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer, this.magicNumber));
//        this.addToBot(new ExhaustAction(abstractPlayer, abstractPlayer, 1, false));
//        this.addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
//        this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, 1, false), 1));
        //AbstractGameAction.AttackEffect.LIGHTNING
        //便利怪物
        this.addToBot(new SFXAction("ATTACK_WHIRLWIND"));
        this.addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
        MonsterGroup group =  AbstractDungeon.getMonsters();

        for(AbstractMonster m1 : group.monsters){
            this.addToBot(new RemoveAllBlockAction(m1, p));
            this.addToBot(new DamageAction(m1, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));

//
//            this.addToBot(new LoseHPAction(m1,m1,this.damage,AbstractGameAction.AttackEffect.LIGHTNING));
        }
//        this.addToBot(new DamageAllEnemiesAction(p,this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));

        this.addToBot(new MakeTempCardInDiscardAction(new VoidCard(), 1));

    }

    @Override
    public AbstractCard makeCopy() {
        return new BreakSpace();
    }



}
