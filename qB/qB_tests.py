from qB import compare

def test_compare():
    assert -1 == compare("1", "1.2")
    assert  0 == compare("1.2", "1.2")
    assert -1 == compare("1.3", "1.3.4")
    assert -1 == compare("1.8", "2")
    assert  0 == compare("1.3.2", "1.3.2.0")
    assert -1 == compare("1.3.2", "1.3.23.0")
    assert  0 == compare("01", "1")
    assert  0 == compare("01", "001")
    assert  0 == compare("01.01", "01.1")
    assert  1 == compare("0.1", "0.0.1")
    assert  0 == compare("1.2.300.00", "1.2.300.00.00.00")
    assert  1 == compare("1.2.3000.00", "1.2.300.00.00.00")


test_compare()