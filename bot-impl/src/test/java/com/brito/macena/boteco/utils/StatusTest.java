package com.brito.macena.boteco.utils;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Status Tests")
public class StatusTest {
    @BeforeAll
    static void setupAll() { System.out.println("Starting Status tests..."); }

    @AfterAll
    static void tearDownAll() { System.out.println("Finishing Status tests..."); }

    @Nested
    @DisplayName("Status Definition Tests")
    class StatusDefinitionTests {
        @Test
        @DisplayName("Status BAD is defined")
        void statusBadIsDefined() {
            assertThat(Status.valueOf("BAD")).isNotNull();
        }

        @Test
        @DisplayName("Status MEDIUM is defined")
        void statusMediumIsDefined() {
            assertThat(Status.valueOf("MEDIUM")).isNotNull();
        }

        @Test
        @DisplayName("Status GOOD is defined")
        void statusGoodIsDefined() {
            assertThat(Status.valueOf("GOOD")).isNotNull();
        }

        @Test
        @DisplayName("Status EXCELLENT is defined")
        void statusExcellentIsDefined() {
            assertThat(Status.valueOf("EXCELLENT")).isNotNull();
        }
    }

    @Nested
    @DisplayName("Status Values Tests")
    class StatusValuesTests {
        @Test
        @DisplayName("Status values contain all statuses")
        void statusValuesContainsAllStatuses() {
            Status[] statuses = Status.values();
            assertThat(statuses).containsExactly(
                    Status.BAD,
                    Status.MEDIUM,
                    Status.GOOD,
                    Status.EXCELLENT);
        }
    }
}