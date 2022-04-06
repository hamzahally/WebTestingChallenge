Feature: Trade ZIL/USDT on Crypto.com

  Scenario: Trade ZIL/USDT pair
    Given the browser is Open
    And user navigates is at the exchange markets
    And user navigates to ZIL/USDT pair
    #When user clicks trade
    Then the ZIL/USDT trade page will show