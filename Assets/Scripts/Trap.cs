using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Trap : MonoBehaviour {

    private float speed = 1.5f;

    private Vector3 direction;

    public double firstCoordinate;
    public double secondCoordinate;

    void Start ()
    {
       direction = transform.up;  
    }
	
	// Update is called once per frame
	void Update ()
    {
        Move();
    }

    private void Move()
    {
        transform.position = Vector3.MoveTowards(transform.position, transform.position + direction, speed * Time.deltaTime);
        if (transform.position.y > firstCoordinate)
        {
            direction = -transform.up;
        }
        else if (transform.position.y < secondCoordinate)
        {
            direction = transform.up;
        }
    }

    protected void OnTriggerEnter2D(Collider2D collider)
    {
        Unit unit = collider.GetComponent<Unit>();

        if (unit && unit is Character)
        {
            unit.ReceiveDamage();
        }
    }
    }
