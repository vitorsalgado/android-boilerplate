# coding: utf-8

Given(/^that I (?:scrolled|scroll) the screen to the (bottom|top|left|right) until a see the element "(.*?)"$/) do |direction, element|
  @page.drag_until_element_is_visible_with_special_query direction.to_sym, element
end

Given(/^that I am on a page which contains '(.*?)'$/) do |page_text|
  @page.is_on_page? page_text
end

######### When #########

When(/^I scroll the screen to the (left|upper left|right|bottom|top) (\d+) times$/) do |direction, times|
  @page.drag_for_specified_number_of_times(direction.to_sym, times.to_i)
end

When(/^I (?:click|clicked|press|pressed) on "(.*?)"$/) do |element|
  @page.touch_screen_element element
end

When(/^I scroll the screen to the (bottom|top|left|right)$/) do |direction|
  @page.drag_to direction.to_sym
end

When(/^(?:I |)restart the app$/) do
  @page.restart_app
end

When(/^(?:select|selected) the default keyboard action key$/) do
  @page.press_user_action_button
end

When(/^(?:select|selected) popup cancel option/) do
  @page.touch_cancelar_popup
  @page.wait_for_progress
end

When(/^(?:select|selected) popup main option/) do
  @page.touch_acao_principal_popup
  @page.wait_for_progress
end

When(/^(?:selecionei|selecionar) a opção de fechar o teclado$/) do
  @page.fechar_teclado
end

######### Then #########

Then(/^I (?:wait|waited) for progress to dismiss/) do
  # wait_for_progress is a method of the base class, so doesn't matter what is
  # the value of the @page variable, because all screens will have this method
  @page.wait_for_progress
end

Then(/^I should see the page '(.*?)'$/) do |page_text|
  @page.is_on_page? page_text
end

Then(/^I should see a page that contains '(.*?)'$/) do |page_text|
  @page.is_on_page? page_text
end

Then(/^I do a print$/) do
  screenshot_embed
end

Then(/^I can see a popup on screen$/) do
  @page.popup_visible?
end
  