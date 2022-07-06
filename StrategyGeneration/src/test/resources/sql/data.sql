-- insert into data_of_indicators(id, decision_by_deal, indicator_type, currency_pair, time_frame) values(1, '{99999, 7777777}', 'SMA', 'EURUSD', 'M1');

INSERT INTO information_of_candles(id, currencypair, timeframe)
VALUES (1, 'EURUSD', 'M1');

INSERT INTO information_of_indicator(id, indicatortype, information_of_candles_id, parameters) VALUES (1, 'SMA', 1, '{"CALCULATE_BY":"OPEN", "PERIOD":5}');
INSERT INTO information_of_indicator(id, indicatortype, information_of_candles_id, parameters) VALUES (2, 'SMA', 2, '{"CALCULATE_BY":"OPEN", "PERIOD":10}');

INSERT INTO specification_of_strategy(id, statistics_of_strategy_id, hash_code, start_score, acceptable_risk,
                                      sum_of_deal_type, sum_of_deal_configuration_data, stop_toss_type,
                                      stop_loss_configuration_data, trailing_stop_type,
                                      trailing_stop_configuration_data, take_profit_type,
                                      take_profit_configuration_data, type_of_deal, information_of_candles_id,
                                      description_to_open_a_deal, description_to_close_a_deal)
VALUES (1, null, 1, 100, 10, 'PERCENT_OF_SCORE', '{"PERCENT_OF_SCORE": 2}', 'FIXED_STOP_LOSS', '{"FIXED": 3}',
        'FIXED', '{"FIXED": 5}', 'FIXED_TAKE_PROFIT', '{"FIXED": 5}', 'BUY', 1, '{1, 2}','{}' )