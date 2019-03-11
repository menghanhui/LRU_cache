from qA import find_overlap

def test_overlap():
    assert "Overlap" == find_overlap([1, 2], [2, 3])
    assert "Overlap" == find_overlap([1, 2], [2, 2])
    assert "Overlap" == find_overlap([1, 5], [2, 6])
    assert "Overlap" == find_overlap([-1, 0], [0, 3.1415])
    assert "Overlap" == find_overlap([-2, -1], [-1.5, 3])
    assert "Overlap" == find_overlap([1, 12], [12, 12])


def test_overlap_reverse():
    assert "Overlap" == find_overlap([12, 1], [2, 3])
    assert "Overlap" == find_overlap([12, 1], [3, 2])


def test_non_overlap():
    assert "Not Overlap" == find_overlap([1, 2], [3, 3])
    assert "Not Overlap" == find_overlap([1, 2], [3, 4])
    assert "Not Overlap" == find_overlap([1, 5], [-2.5, 0])
    assert "Not Overlap" == find_overlap([-1, 0], [1.2, 3.1415])
    assert "Not Overlap" == find_overlap([1, 1], [0, 0])


def test_non_overlap_reverse():
    assert "Not Overlap" == find_overlap([12, 6], [-2, -3])


def test_exception():
    try:
        find_overlap([12, 6], [])
    except Exception as error:
        assert "The input is not valid" == str(error)

    try:
        find_overlap([12, 6], [1])
    except Exception as error:
        assert "The input is not valid" == str(error)




test_overlap()
test_overlap_reverse()
test_non_overlap()
test_non_overlap_reverse()
test_exception()