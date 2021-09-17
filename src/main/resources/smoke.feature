Feature: Smoke
  As a user
  I would like to check that the acute functionalities of program is working fine
  I can do this by performing testing of the software product

  Scenario Outline: Buttons and banners on Homepage should open required page
    Given User opens '<homePage>' page
    And User checks central buttons
    And User checks image on homepage
    When User clicks home page '<button>' button
    Then Page with title '<title>' is displayed
    Examples:
      | homePage              | button     | title |
      | https://www.asos.com/ | SHOP MEN   | Men   |
      | https://www.asos.com/ | SHOP WOMEN | Women |

  Scenario Outline: After clicking on the logo, the previously selected page should opens
    Given User opens '<homePage>' page
    And User checks header buttons
    And User clicks header '<button>' button
    And Page with title '<title>' is displayed
    When User checks logo
    And User clicks logo
    Then Page with title '<finalTitle>' is displayed

    Examples:
      | homePage              | button     | title    | finalTitle |
      | https://www.asos.com/ | MEN        | Men      | Men        |
      | https://www.asos.com/ | WOMEN      | Women    | Women      |
      | https://www.asos.com/ | SavedItems | Saved    | ASOS       |
      | https://www.asos.com/ | Bag Items  | Shopping | ASOS       |