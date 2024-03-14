class Node <T>{

  T _data;
  Node<T>? _next; 
  Node<T>? _previous;

  Node(this._data);

  T get data => _data;

  set data(T value) {
    _data = value;
  }

  Node<T>? get next => _next;

  set next(Node<T>? value) {
    _next = value;
  }

  Node<T>? get previous => _previous;

  set previous(Node<T>? value){
    _previous = value;
  }
  @override
  String toString(){
    return "$_data";
  }

}