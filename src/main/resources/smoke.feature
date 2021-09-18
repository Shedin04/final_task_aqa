Feature: Smoke
  As a user
  I would like to check that the acute functionalities of program is working fine
  I can do this by performing testing of the software product

  Scenario Outline: User entering the site can select the required category of goods and check count of found styles (rounded)
    Given User opens '<homePage>' page
    And User checks central buttons
    And User checks image on homepage
    When User clicks type of shop '<button>' button
    And Page with title '<title>' is displayed
    And User checks goods features page
    And User clicks feature '<feature>' button
    And Page with title '<featureTitle>' is displayed
    Then count of styles is '<countOfStyles>'

    Examples:
      | homePage              | button     | title | feature                  | featureTitle | countOfStyles |
      | https://www.asos.com/ | SHOP MEN   | Men   | SMARTY PANTS             | Men          | 2,045         |
      | https://www.asos.com/ | SHOP WOMEN | Women | TEAM TOPSHOP             | Topshop      | 3,430         |
      | https://www.asos.com/ | SHOP MEN   | Men   | ASOS X POLO RALPH LAUREN | Ralph        | 610           |
      | https://www.asos.com/ | SHOP WOMEN | Women | THE NORTH FACE           | The          | 648           |

  Scenario Outline: User can find and open required pages in the header, filter goods presented there
    Given User opens '<homePage>' page
    And User checks header buttons
    And User clicks header '<button>' button
    And Page with title '<title>' is displayed
    And User checks logo
    And User clicks logo
    And Page with title '<title>' is displayed
    And User hover mouse over '<mainCategory>' category
    And User clicks element '<element>' in header
    And User checks '<pageName>' name of page
    And User checks filters
    When User clicks '<filter>' filter
    And User checks filter sections
    And User selects filter section '<section>'
    Then User checks that name of products contains '<productName>'

    Examples:
      | homePage              | button  | title | mainCategory | element          | pageName                    | filter | section | productName |
      | https://www.asos.com/ | MEN     | Men   | Sale         | SALE Tracksuits  | Sale: Tracksuits & Joggers  | Brand  | Fila    | Fila        |
      | https://www.asos.com/ | WOMEN   | Women | Outlet       | Maternity        | Outlet: Maternity           | Size   | UK 12   | Jaded       |

  Scenario Outline: User can find any product through search
    Given User opens '<homePage>' page
    And User checks search box
    When User enters request '<request>'
    And User clicks submit search button
    Then User checks that search result '<result>'
    And count of styles is '<foundStyles>'

    Examples:
      | homePage              | request               | result                      | foundStyles |
      | https://www.asos.com/ | Under Armour          | Under Armour                | 957         |
      | https://www.asos.com/ | Paris Saint Germain   | Paris Saint Germain         | 16          |
      | https://www.asos.com/ | 4qrqwrw45151          | NOTHING MATCHES YOUR SEARCH | 0           |
      | https://www.asos.com/ | New Balance           | New Balance                 | 712         |