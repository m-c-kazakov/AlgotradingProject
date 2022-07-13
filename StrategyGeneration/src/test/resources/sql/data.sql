TRUNCATE information_of_candles;
TRUNCATE information_of_indicator;
TRUNCATE statistics_of_strategy;
TRUNCATE specification_of_strategy;

INSERT INTO information_of_candles(hash_code, currency_pair, time_frame)
VALUES (3, 'EURUSD', 'M1');

INSERT INTO information_of_indicator(hash_code, indicator_type, information_of_candles, parameters) VALUES (3, 'SMA', 1, '{"CALCULATE_BY":"OPEN", "PERIOD":5}');
INSERT INTO information_of_indicator(hash_code, indicator_type, information_of_candles, parameters) VALUES (4, 'SMA', 2, '{"CALCULATE_BY":"OPEN", "PERIOD":10}');

INSERT INTO statistics_of_strategy(specification_of_strategy_id, score, value_of_acceptable_risk,
                                   maximum_percent_drawdown_from_start_score, average_percent_drawdown_of_score,
                                   maximum_value_from_score, number_of_winning_deals, number_of_losing_deals)
VALUES (1, 5000, 5, 2, 2, 7000, 20, 20);


INSERT INTO specification_of_strategy(statistics_of_strategy_id, hash_code, date_of_creation, start_score, acceptable_risk,
                                      sum_of_deal_type, sum_of_deal_configuration_data, stop_loss_type,
                                      stop_loss_configuration_data, trailing_stop_type,
                                      trailing_stop_configuration_data, take_profit_type,
                                      take_profit_configuration_data, type_of_deal, information_of_candles,
                                      description_to_open_a_deal, description_to_close_a_deal)
VALUES (1, 123123123, current_date, 100, 10, 'PERCENT_OF_SCORE', '{"PERCENT_OF_SCORE": 2}', 'FIXED_STOP_LOSS', '{"FIXED_STOP_LOSS": 3}',
        'FIXED_TRAILING_STOP_TYPE', '{"FIXED_TRAILING_STOP": 5}', 'FIXED_TAKE_PROFIT', '{"FIXED_TAKE_PROFIT": 7}', 'BUY', 1, '{1, 2}','{}' )