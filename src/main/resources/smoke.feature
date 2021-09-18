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
      | https://www.asos.com/ | SHOP MEN   | Men   | ASOS X POLO RALPH LAUREN | Ralph        | 626           |
      | https://www.asos.com/ | SHOP WOMEN | Women | THE NORTH FACE           | The          | 654           |

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

    Examples:
      | homePage              | button  | title | mainCategory | element          | pageName                    |
      | https://www.asos.com/ | MEN     | Men   | Sale         | SALE Tracksuits  | Sale: Tracksuits & Joggers  |
      | https://www.asos.com/ | WOMEN   | Women | Outlet       | Maternity        | Outlet: Maternity           |