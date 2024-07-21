public class RpcGuessing {
    private int point;
    RpcGuessing(int _point){
        this.point = _point;
    }

    public int rpp(int p){
        this.point += p;
        return this.point;
    }

    public static void main(String[] args){
        RpcGuessing rpc = new RpcGuessing(0);
        int _rpp = rpc.rpp(2);
        System.out.println("rpp = " + _rpp);
    }
}
