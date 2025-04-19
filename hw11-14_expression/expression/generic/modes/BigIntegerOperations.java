package expression.generic.modes;

import expression.exceptions.DivisionByZero;

import java.math.BigInteger;

public class BigIntegerOperations implements GenericOperations<BigInteger> {

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) throws DivisionByZero {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZero("BigInteger divide by zero");
        }
        return a.divide(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger toMode(String a) {
        return new BigInteger(a);
    }
}
