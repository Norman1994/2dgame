using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UFOMonster : Monster
{

    private float speed = 3.0f;

    private Vector3 direction;

    private SpriteRenderer sprite;

    public static bool damage;
    private int liveCount;

    protected override void Awake()
    {
        sprite = GetComponentInChildren<SpriteRenderer>();
        liveCount = 3;
    }

    protected override void Start()
    {
        direction = transform.right;
        damage = false;
    }

    protected override void Update()
    {
        Move();
    }

    private void Move()
    {
        transform.position = Vector3.MoveTowards(transform.position, transform.position + direction, speed * Time.deltaTime);
    }

    protected override void OnTriggerEnter2D(Collider2D collider)
    {
        Unit unit = collider.GetComponent<Unit>();

        if (unit && unit is Character)
        {
            unit.ReceiveDamage();
        }

        Bullet bullet = collider.gameObject.GetComponent<Bullet>();

        if (bullet && bullet.Parent != gameObject)
        {
            liveCount--;
            if (liveCount == 0)
                ReceiveDamage();
        }

        if (collider.tag == "Trigger")
        {
            direction *= -1.0F;
            sprite.flipX = true;
        }

        if (collider.tag == "Trigger2")
        {
            direction *= -1.0F;
            sprite.flipX = false;
        }
    }
}
