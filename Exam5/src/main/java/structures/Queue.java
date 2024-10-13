package structures;

import exceptions.StackException;

public class Queue<T> implements IQueue<T>{

    private IStack<T> stackReverse;
    private IStack<T> stackInOrder;

    public Queue(){
        stackReverse = new Stack<>();
        stackInOrder = new Stack<>();
    }

    private void passStackInOrder(){
        while(!stackReverse.isEmpty()){
            try{
                stackInOrder.push(stackReverse.pop());
            } catch(StackException e){

            }
        }
    }

    private void passStackReverse(){
        while(!stackInOrder.isEmpty()){
            try{
                stackReverse.push(stackInOrder.pop());
            } catch(StackException e){

            }
        }
    }

    @Override
    public void enqueue(T element) {
        if(stackReverse.isEmpty()){
            passStackReverse();
        }

        stackReverse.push(element);
    }

    @Override
    public T dequeue() {
        T element = null;

        if(stackInOrder.isEmpty()){
            passStackInOrder();
        }

        try{
            element = stackInOrder.pop();
        } catch (StackException e) {

        }

        return element;
    }

    @Override
    public T front() {
        T element = null;

        if(!isEmpty()){
            if(stackInOrder.isEmpty()){
                passStackInOrder();
            }
            try{
                element = stackInOrder.top();
            } catch (StackException e){

            }

        }

        return element;
    }

    @Override
    public boolean isEmpty() {
        boolean generalEmpty = false;

        if(stackReverse.isEmpty() && stackInOrder.isEmpty()){
            generalEmpty = true;
        }

        return generalEmpty;
    }
}
