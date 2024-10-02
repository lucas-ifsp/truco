package com.eduardo.vinicius.camaleaotruqueiro;

import com.bueno.spi.model.CardRank;
import com.bueno.spi.model.CardSuit;
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

public class CamaleaoTruqueiroTest {

    private CamaleaoTruqueiro camaleao;

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

        TrucoCard greaterCard = camaleao.getGreaterCard(cards, vira);
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
        softly.assertThat(camaleao.numberOfManilhas(zeroManilhasCards)).isEqualTo(0);
        softly.assertThat(camaleao.numberOfManilhas(oneManilhasCards)).isEqualTo(1);
        softly.assertThat(camaleao.numberOfManilhas(twoManilhasCards)).isEqualTo(2);
        softly.assertThat(camaleao.numberOfManilhas(threeManilhasCards)).isEqualTo(3);
    }

    //temos carta alta
        // temos 0 carta alta
        // temos 1 carta alta
        // temos 2 carta alta
        // temos 3 carta alta

    //temos carta baixa



    //estamos ganhando

    //estamos perdendo

}