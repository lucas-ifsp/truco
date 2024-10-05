package com.eduardo.vinicius.camaleaotruqueiro;

import com.bueno.spi.model.CardRank;
import com.bueno.spi.model.CardSuit;
import com.bueno.spi.model.GameIntel;
import com.bueno.spi.model.TrucoCard;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;

public class CamaleaoTruqueiroTest {

    private CamaleaoTruqueiro camaleao;

    private GameIntel.StepBuilder builder;

    @BeforeEach
    public void config() {
        camaleao = new CamaleaoTruqueiro();
    }

    //maior carta
    @Test
    @DisplayName("Should return the greater rank card")
    void shouldReturnTheGreaterRankCard() {
        TrucoCard vira = TrucoCard.of(CardRank.ACE, CardSuit.CLUBS);

        List<TrucoCard> cards = Arrays.asList(
                TrucoCard.of(CardRank.TWO, CardSuit.CLUBS),
                TrucoCard.of(CardRank.TWO, CardSuit.HEARTS),
                TrucoCard.of(CardRank.THREE, CardSuit.CLUBS)
        );

        TrucoCard greaterCard = camaleao.getGreatestCard(cards, vira);
        assertEquals(greaterCard, TrucoCard.of(CardRank.TWO, CardSuit.CLUBS));
    }


    //menor carta
    @Test
    @DisplayName("Should return the lowest card")
    void shouldReturnTheLowestCard() {
        TrucoCard vira = TrucoCard.of(CardRank.THREE,CardSuit.CLUBS);

        List<TrucoCard> cards = Arrays.asList(
                TrucoCard.of(CardRank.FOUR,CardSuit.CLUBS),
                TrucoCard.of(CardRank.QUEEN,CardSuit.CLUBS),
                TrucoCard.of(CardRank.THREE,CardSuit.CLUBS)
        );

        TrucoCard lowest = camaleao.getLowestCard(cards, vira);
        assertThat(lowest).isEqualTo(TrucoCard.of(CardRank.QUEEN,CardSuit.CLUBS));
    }


    @Test
    @DisplayName("Shoult return number of manilhas")
    void shoultReturnoNumberOfManilhas() {
        SoftAssertions softly = new SoftAssertions();
        //Given
        TrucoCard vira = TrucoCard.of(CardRank.THREE, CardSuit.HEARTS);

        List<TrucoCard> zeroManilhasCards = Arrays.asList(
                TrucoCard.of(CardRank.FIVE, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.QUEEN, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<TrucoCard> oneManilhasCards = Arrays.asList(
                TrucoCard.of(CardRank.FIVE, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.FOUR, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.THREE, CardSuit.DIAMONDS)
        );
        List<TrucoCard> twoManilhasCards = Arrays.asList(
                TrucoCard.of(CardRank.FIVE, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.FOUR, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.FOUR, CardSuit.CLUBS)
        );
        List<TrucoCard> threeManilhasCards = Arrays.asList(
                TrucoCard.of(CardRank.FOUR, CardSuit.SPADES),
                TrucoCard.of(CardRank.FOUR, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.FOUR, CardSuit.CLUBS)
        );
        //Then
        softly.assertThat(camaleao.numberOfManilhas(zeroManilhasCards, vira)).as("Zero manilhas").isEqualTo(0);
        softly.assertThat(camaleao.numberOfManilhas(oneManilhasCards, vira)).as("One manilha").isEqualTo(1);
        softly.assertThat(camaleao.numberOfManilhas(twoManilhasCards, vira)).as("Two manilhas").isEqualTo(2);
        softly.assertThat(camaleao.numberOfManilhas(threeManilhasCards, vira)).as("Three manilhas").isEqualTo(3);
        softly.assertAll();
    }
    
    @Test
    @DisplayName("shouldReturnOneHighCards")
    void shouldReturnOneHighCard(){
        TrucoCard vira = TrucoCard.of(CardRank.QUEEN, CardSuit.HEARTS);

        TrucoCard card1 = TrucoCard.of(CardRank.FIVE, CardSuit.DIAMONDS);
        TrucoCard card2 = TrucoCard.of(CardRank.QUEEN, CardSuit.HEARTS);
        TrucoCard card3 = TrucoCard.of(CardRank.THREE, CardSuit.CLUBS);
        List<TrucoCard> handCards = Arrays.asList(card1, card2, card3);

        int numberOfHighCard = camaleao.getNumberOfHighCards(handCards,vira);
        assertEquals(1, numberOfHighCard);
    }

    @Test
    @DisplayName("shouldReturnTwoHighCards")
    void shouldReturnTwoHighCards(){
        TrucoCard vira = TrucoCard.of(CardRank.QUEEN, CardSuit.HEARTS);

        TrucoCard card1 = TrucoCard.of(CardRank.ACE, CardSuit.DIAMONDS);
        TrucoCard card2 = TrucoCard.of(CardRank.QUEEN, CardSuit.HEARTS);
        TrucoCard card3 = TrucoCard.of(CardRank.THREE, CardSuit.CLUBS);
        List<TrucoCard> handCards = Arrays.asList(card1, card2, card3);

        int numberOfHighCard = camaleao.getNumberOfHighCards(handCards,vira);
        assertEquals(2, numberOfHighCard);
    }

    @Test
    @DisplayName("shouldReturnThreeHighCards")
    void shouldReturnThreeHighCards(){
        TrucoCard vira = TrucoCard.of(CardRank.QUEEN, CardSuit.HEARTS);

        TrucoCard card1 = TrucoCard.of(CardRank.ACE, CardSuit.DIAMONDS);
        TrucoCard card2 = TrucoCard.of(CardRank.JACK, CardSuit.HEARTS);
        TrucoCard card3 = TrucoCard.of(CardRank.ACE, CardSuit.CLUBS);
        List<TrucoCard> handCards = Arrays.asList(card1, card2, card3);

        int numberOfHighCard = camaleao.getNumberOfHighCards(handCards,vira);
        assertEquals(3, numberOfHighCard);
    }

    @Test
    @DisplayName("shouldReturnIfTheBotIsWinning")
    void shouldReturnIfTheBotIsWinning(){
        SoftAssertions softly = new SoftAssertions();
        boolean isWinning01 = camaleao.isWinning(10,9);
        boolean isWinning02 = camaleao.isWinning(8,9);
        boolean isWinning03 = camaleao.isWinning(0,0);

        softly.assertThat(isWinning01).isTrue();
        softly.assertThat(isWinning02).isFalse();
        softly.assertThat(isWinning03).isFalse();
    }

    @Test
    @DisplayName("Should return a median card that is a Manilha")
    void shouldReturnAMedianCardThatIsAManilha() {
        SoftAssertions softly = new SoftAssertions();
        TrucoCard vira = TrucoCard.of(CardRank.KING, CardSuit.HEARTS);
        List<TrucoCard> cards  = Arrays.asList(
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS),
                TrucoCard.of(CardRank.ACE, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.SIX, CardSuit.HEARTS)
        );
        TrucoCard medianCard = camaleao.getMedianCard(cards, vira);
        softly.assertThat(medianCard.isManilha(vira)).isTrue();
        softly.assertThat(medianCard.getRank()).isEqualTo(CardRank.ACE);
        softly.assertThat(medianCard.getSuit()).isEqualTo(CardSuit.DIAMONDS);
        softly.assertAll();
    }

    @Test
    @DisplayName("Should return a median card that is the same as the lowest")
    void shouldReturnAMedianCardThatIsTheSameAsTheLowest() {
        SoftAssertions softly = new SoftAssertions();
        TrucoCard vira = TrucoCard.of(CardRank.KING, CardSuit.HEARTS);
        List<TrucoCard> cards  = Arrays.asList(
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS),
                TrucoCard.of(CardRank.SIX, CardSuit.HEARTS),
                TrucoCard.of(CardRank.SIX, CardSuit.HEARTS)
        );
        TrucoCard lowestCard = camaleao.getLowestCard(cards,vira);
        TrucoCard medianCard = camaleao.getMedianCard(cards, vira);
        softly.assertThat(medianCard).isEqualTo(lowestCard);
        softly.assertAll();
    }

    @Test
    @DisplayName("Should return a median card that is the same as the greatest")
    void shouldReturnAMedianCardThatIsTheSameAsTheGreatest() {
        SoftAssertions softly = new SoftAssertions();
        TrucoCard vira = TrucoCard.of(CardRank.KING, CardSuit.HEARTS);
        List<TrucoCard> cards  = Arrays.asList(
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS),
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS),
                TrucoCard.of(CardRank.SIX, CardSuit.HEARTS)
        );
        TrucoCard greatestCard = camaleao.getGreatestCard(cards,vira);
        TrucoCard medianCard = camaleao.getMedianCard(cards, vira);
        softly.assertThat(medianCard).isEqualTo(greatestCard);
        softly.assertAll();
    }

    @Test
    @DisplayName("Should return the number of worst cards then the median card when it is Copas")
    void shouldReturnTheNumberOfWorstCardsThenTheMedianCardWhenItCopas() {
        TrucoCard vira = TrucoCard.of(CardRank.QUEEN,CardSuit.DIAMONDS);
        List<TrucoCard> cards = Arrays.asList(
                TrucoCard.of(CardRank.JACK, CardSuit.CLUBS),
                TrucoCard.of(CardRank.JACK, CardSuit.HEARTS),
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS)
        );
        TrucoCard medianCard = camaleao.getMedianCard(cards, vira);
        assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, vira)).isEqualTo(38);
    }

    @Test
    @DisplayName("Should return the number of worst cards then the median card when it is Espadilhas")
    void shouldReturnTheNumberOfWorstCardsThenTheMedianCardWhenItIsEspadilhas() {
        TrucoCard vira = TrucoCard.of(CardRank.QUEEN,CardSuit.DIAMONDS);
        List<TrucoCard> cards = Arrays.asList(
                TrucoCard.of(CardRank.JACK, CardSuit.CLUBS),
                TrucoCard.of(CardRank.JACK, CardSuit.SPADES),
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS)
        );
        TrucoCard medianCard = camaleao.getMedianCard(cards, vira);
        assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, vira)).isEqualTo(37);
    }

    @Test
    @DisplayName("Should return the number of worst cards then the median card when it is Ouros")
    void shouldReturnTheNumberOfWorstCardsThenTheMedianCardWhenItIsOuros() {
        TrucoCard vira = TrucoCard.of(CardRank.QUEEN,CardSuit.DIAMONDS);
        List<TrucoCard> cards = Arrays.asList(
                TrucoCard.of(CardRank.JACK, CardSuit.CLUBS),
                TrucoCard.of(CardRank.JACK, CardSuit.DIAMONDS),
                TrucoCard.of(CardRank.ACE, CardSuit.CLUBS)
        );
        TrucoCard medianCard = camaleao.getMedianCard(cards, vira);
        assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, vira)).isEqualTo(36);
    }

    @Test
    @DisplayName("Should return the number of worst cards then the median card when vira is greater or equals then median card")
    void shouldReturnTheNumberOfWorstCardsThenTheMedianCardWhenViraIsGreaterOrEqualsThenMedianCard() {
        SoftAssertions softly= new SoftAssertions();

        TrucoCard viraSomeRankThenMedianCard = TrucoCard.of(CardRank.JACK,CardSuit.DIAMONDS);
        TrucoCard viraGreaterRankThenMedianCard = TrucoCard.of(CardRank.KING,CardSuit.DIAMONDS);
        TrucoCard medianCard = TrucoCard.of(CardRank.JACK, CardSuit.SPADES);
        softly.assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, viraSomeRankThenMedianCard)).isEqualTo(20);
        softly.assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, viraGreaterRankThenMedianCard)).isEqualTo(20);
        softly.assertAll();
    }



    @Test
    @DisplayName("Should return the number of worst cards then the median card when vira is lowest then median card")
    void shouldReturnTheNumberOfWorstCardsThenTheMedianCardWhenViraIsLowestThenMedianCard() {
        SoftAssertions softly= new SoftAssertions();

        TrucoCard vira= TrucoCard.of(CardRank.SEVEN,CardSuit.DIAMONDS);
        TrucoCard medianCard = TrucoCard.of(CardRank.JACK, CardSuit.SPADES);
        softly.assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, vira)).isEqualTo(15);
        vira= TrucoCard.of(CardRank.FOUR,CardSuit.DIAMONDS);
        medianCard = TrucoCard.of(CardRank.SIX, CardSuit.SPADES);
        softly.assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, vira)).isEqualTo(3);
        softly.assertAll();
    }

    @Test
    @DisplayName("Should return the number of worst cards then the median card when ranks of vira and median card are both 1")
    void shouldReturnTheNumberOfWorstCardsThenTheMedianCardWhenRanksOfViraAndMedianCardAreBoth1() {
        TrucoCard vira = TrucoCard.of(CardRank.FOUR,CardSuit.DIAMONDS);
        TrucoCard medianCard = TrucoCard.of(CardRank.FOUR, CardSuit.SPADES);
        assertThat(camaleao.getNumberOfCardWorstThanMedianCard(medianCard, vira)).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return the chances of player has a absolut victory when he or she has best cards")
    void shouldReturnTheChancesOfPlayerHasAAbsolutVictoryWhenHeOrSheHasGreatestCards() {
        TrucoCard vira = TrucoCard.of(CardRank.THREE, CardSuit.SPADES);
        List<TrucoCard> playersHand = Arrays.asList(
                TrucoCard.of(CardRank.FOUR, CardSuit.CLUBS),
                TrucoCard.of(CardRank.FOUR, CardSuit.HEARTS),
                TrucoCard.of(CardRank.FOUR, CardSuit.SPADES)
        );

        Float result = camaleao.getProbabilityOfAbsolutVictoryHand(playersHand, vira);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Should return the chances of player has a absolut victory when he or she has worst cards")
    void shouldReturnTheChancesOfPlayerHasAAbsolutVictoryWhenHeOrSheHasWorstestCards() {
        TrucoCard vira = TrucoCard.of(CardRank.TWO, CardSuit.HEARTS);
        List<TrucoCard> playersHand = Arrays.asList(
                TrucoCard.of(CardRank.FOUR, CardSuit.HEARTS),
                TrucoCard.of(CardRank.FOUR, CardSuit.SPADES),
                TrucoCard.of(CardRank.FOUR, CardSuit.DIAMONDS)
        );

        Float result = camaleao.getProbabilityOfAbsolutVictoryHand(playersHand, vira);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return if the bot plays first")
    void shouldReturnIfTheBotPlaysFirst(){
        TrucoCard vira = TrucoCard.of(CardRank.TWO, CardSuit.HEARTS);
        List<TrucoCard> myCards = List.of();
        List<TrucoCard> openCards = List.of(vira);

        builder = GameIntel.StepBuilder.with()
                .gameInfo(List.of(),openCards,vira,1)
                .botInfo(myCards,0)
                .opponentScore(1);

        assertTrue(camaleao.theBotPlaysFirst(builder.build()));
    }


    //estamos ganhando

    //estamos perdendo

}